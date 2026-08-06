package mx.edu.unsis.sige.auth.adapter.out.persistence.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "refresh_tokens", schema = "sige_users")
@EntityListeners(AuditingEntityListener.class)
public class RefreshTokenEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEntity account;

    @Column(name = "token_hash", nullable = false, unique = true)
    private String tokenHash;

    @Column(name = "device_info")
    private String deviceInfo;

    @Column(name = "is_revoked", nullable = false)
    private boolean revoked;

    @Column(name = "expires_at", nullable = false)
    private OffsetDateTime expiresAt;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    protected RefreshTokenEntity() {}

    public RefreshTokenEntity(AccountEntity account, String tokenHash,
                               String deviceInfo, OffsetDateTime expiresAt) {
        this.account = account;
        this.tokenHash = tokenHash;
        this.deviceInfo = deviceInfo;
        this.expiresAt = expiresAt;
        this.revoked = false;
    }

    public UUID getId() { return id; }
    public AccountEntity getAccount() { return account; }
    public String getTokenHash() { return tokenHash; }
    public boolean isRevoked() { return revoked; }
    public OffsetDateTime getExpiresAt() { return expiresAt; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
}