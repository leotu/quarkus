package io.quarkus.it.keycloak;

import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.SecurityAttribute;
import io.quarkus.test.security.TestSecurity;
import io.restassured.RestAssured;

@QuarkusTest
@TestHTTPEndpoint(ProtectedResource.class)
public class TestSecurityLazyAuthTest {

    @Test
    @TestSecurity(user = "user1", roles = "viewer")
    public void testWithDummyUser() {
        RestAssured.when().get("test-security").then()
                .body(is("user1"));
    }

    @Test
    @TestSecurity(user = "userOidc", roles = "viewer", attributes = {
            @SecurityAttribute(key = "claim.email", value = "user@gmail.com")
    })
    public void testJwtWithDummyUser() {
        RestAssured.when().get("test-security-oidc").then()
                .body(is("userOidc:viewer:user@gmail.com"));
    }

}
