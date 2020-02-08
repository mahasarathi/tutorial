/**
 * 
 */
package com.example.demo.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

/**
 * @author mahasarathi
 *
 */

public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.of("Admin");
	}

}
