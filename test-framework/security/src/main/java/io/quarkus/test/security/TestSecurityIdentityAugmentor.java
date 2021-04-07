package io.quarkus.test.security;

import io.quarkus.security.identity.SecurityIdentity;

public interface TestSecurityIdentityAugmentor {
    SecurityIdentity augment(SecurityIdentity identity);
}
