package uz.logistics.ecourier.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditAwareImpl implements AuditorAware<Long> {
    private static final Long CURRENT_AUDITOR_ID = 1L;

    @Override
    public Optional<Long> getCurrentAuditor() {
        return Optional.of(CURRENT_AUDITOR_ID);
    }
}
