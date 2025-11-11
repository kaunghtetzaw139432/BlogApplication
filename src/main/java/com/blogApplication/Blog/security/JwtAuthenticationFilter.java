package com.blogApplication.Blog.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private Logger logger = org.slf4j.LoggerFactory.getLogger(OncePerRequestFilter.class);
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private CustomUserDetailsService userDetailsService;

   @Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
    
    final String authHeader = request.getHeader("Authorization");
    String username = null;
    String jwt = null; // String များကို ပိုမိုရှင်းလင်းစွာ စတင် သတ်မှတ်ပါ
    
    // 1. Authorization Header မရှိပါက ချက်ချင်း Filter Chain ကို ဆက်ပြီး return ဖြင့် ထွက်ပါ
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        filterChain.doFilter(request, response);
        return; // 👈 ဤ return သည် အရေးကြီးဆုံးဖြစ်သည်။
    }
    
    // 2. Token နှင့် Username ကို ရယူပါ (ဤနေရာသို့ ရောက်လာလျှင် Header ရှိနေပြီဖြစ်သည်)
    jwt = authHeader.substring(7);
    
    try {
        username = jwtHelper.getUsernameFromToken(jwt);
    } catch (Exception e) {
        // Token ပျက်စီးနေခြင်း၊ သက်တမ်းကုန်ခြင်း စသည်တို့ကို ဤနေရာတွင် log ထုတ်နိုင်သည်
        logger.error("Error retrieving username from token: {}", e.getMessage());
    }

    // 3. Security Context ကို သတ်မှတ်ပါ
    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
        
        if (jwtHelper.validateToken(jwt, userDetails)) {
            // Context ထဲသို့ Authentication ကို ထည့်သွင်းခြင်း (မှန်ကန်သည်)
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null, 
                    userDetails.getAuthorities());

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        } else {
            logger.info("Token validation fails !!");
        }
    }
    
    // 4. Logic အားလုံးပြီးဆုံးမှ Filter Chain ကို ဆက်လုပ်ပါ (တစ်ကြိမ်သာ)
    filterChain.doFilter(request, response);
}

}
