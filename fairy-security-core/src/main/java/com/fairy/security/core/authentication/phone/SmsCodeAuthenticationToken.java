/**
 * 
 */
package com.fairy.security.core.authentication.phone;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

/**
 * @author Administrator
 *
 */
public class SmsCodeAuthenticationToken extends AbstractAuthenticationToken {
	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	// ~ Instance fields
	// ================================================================================================

	private final Object principal;

	// ~ Constructors
	// ===================================================================================================

	/**
	 * This constructor can be safely used by any code that wishes to create a
	 * <code>UsernamePasswordAuthenticationToken</code>, as the {@link #isAuthenticated()}
	 * will return <code>false</code>.
	 *
	 */
	public SmsCodeAuthenticationToken(String phone) {
		super(null);
		this.principal = phone;
		setAuthenticated(false);
	}


	public SmsCodeAuthenticationToken(Object principal,
			Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = principal;
		super.setAuthenticated(true); // must use super, as we override
	}

	// ~ Methods
	// ========================================================================================================

	public Object getPrincipal() {
		return this.principal;
	}

	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		if (isAuthenticated) {
			throw new IllegalArgumentException(
					"Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
		}

		super.setAuthenticated(false);
	}


 
	@Override
	public Object getCredentials() {
		return null;
	}


}
