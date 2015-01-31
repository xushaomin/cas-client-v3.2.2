/**
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.jasig.cas.client.util;

import org.jasig.cas.client.validation.Assertion;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Places the assertion in a ThreadLocal such that other resources can access it that do not have access to the web tier session.
 *
 * @author Scott Battaglia
 * @version $Revision: 11728 $ $Date: 2007-09-26 14:20:43 -0400 (Tue, 26 Sep 2007) $
 * @since 3.0
 */
public final class AssertionThreadLocalFilter extends AbstractConfigurationFilter {
	
	/**
     * 过滤地址集合
     */
	protected RequestURIFilter excludeFilter;
    
    /**
     * 过滤地址
     */
	private String exclusions = null;

    public void init(final FilterConfig filterConfig) throws ServletException {
    	//获取需要过滤拦截地址
        setExclusions(getPropertyFromInitParams(filterConfig, "exclusions", null));
        String filterExclusions = exclusions  + "," + CasPropertiesConfig.getString(CasPropertiesConfig.FILTER_EXCLUSIONS);
		excludeFilter = new RequestURIFilter(filterExclusions);
    }

    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpSession session = request.getSession(false);
        final Assertion assertion = (Assertion) (session == null ? request.getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION) : session.getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION));

        String path = ServletUtil.getResourcePath(request);
        if (isExcluded(path)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        try {
            AssertionHolder.setAssertion(assertion);
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            AssertionHolder.clear();
        }
    }

    public void destroy() {
        // nothing to do
    }
    
    public void setExclusions(String exclusions) {
		this.exclusions = exclusions;
	}
    
    public boolean isExcluded(String path) {
        // 如果指定了excludes，并且当前requestURI匹配任何一个exclude pattern，
        // 则立即放弃控制，将控制还给servlet engine。
        // 但对于internal path，不应该被排除掉，否则internal页面会无法正常显示。
        if (excludeFilter != null && excludeFilter.matches(path)) {
        	return true;
        }
        return false;
    }
}
