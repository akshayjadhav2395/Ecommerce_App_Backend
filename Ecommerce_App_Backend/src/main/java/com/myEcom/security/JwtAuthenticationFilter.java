package com.myEcom.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //get token from header
        //Authorization = Bearer 28172722.27222722.37272722
        String requestToken = request.getHeader("Authorization");
        logger.info("message", requestToken);

        String username=null;
        String jwtToken=null;

        //checking weather token is valid or not
        if(requestToken !=null && requestToken.trim().startsWith("Bearer"))
        {
            //get token from request
           jwtToken = requestToken.substring(7);

           //get username from token
            try {
                this.jwtHelper.getUsernameFromToken(jwtToken);
            }
            catch (ExpiredJwtException jwtException){
                logger.info("message", "Jwt Token Expired..!");
            }
            catch (MalformedJwtException malformedJwtException)
            {
                logger.info("message", "Invalid Jwt..!");
            }
            catch (IllegalArgumentException illegalArgumentException)
            {
                logger.info("message", "Unable to get Token..!");
            }

            //validate Token
            if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
            {
                 //validate
                UserDetails  userDetails = this.userDetailsService.loadUserByUsername(username);
                if(this.jwtHelper.validateToken(jwtToken, userDetails))
                {
                    //authentication
                    UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
                else {
                    logger.info("message", "Invalid Jwt Token..!");
                }

            }
            else {
                logger.info("user message", "Username is Invalid or Auth is already there..!");
            }
        }
        else {
            logger.info("token message", "Token doesn't start with bearer..!");
        }
        filterChain.doFilter(request, response);
    }
}
