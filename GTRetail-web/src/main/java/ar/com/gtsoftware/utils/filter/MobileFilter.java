/*
 * Copyright 2016 GT Software.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ar.com.gtsoftware.utils.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
public class MobileFilter implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1,
            FilterChain arg2) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) arg0;
        String userAgent = req.getHeader("user-agent");
        String accept = req.getHeader("Accept");

        if (userAgent != null && accept != null) {
            UserAgentInfo agent = new UserAgentInfo(userAgent, accept);
            HttpServletResponse res = (HttpServletResponse) arg1;
            if (agent.isMobileDevice()) {

                res.sendRedirect("protected/mobile/index.xhtml");
                return;
            }

            arg2.doFilter(arg0, arg1);

        }

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

}
