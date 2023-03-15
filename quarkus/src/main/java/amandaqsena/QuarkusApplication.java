package amandaqsena;

import static org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeIn.HEADER;
import static org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType.HTTP;

import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

@OpenAPIDefinition(
    info = @Info(
        title = "Quarkus API",
        version = "1.0.0"
    ),
    servers = {@Server(url = "/")},
    security = {@SecurityRequirement(name = "BasicAuth")})
@SecurityScheme(
    securitySchemeName = "BasicAuth",
    type = HTTP,
    in = HEADER,
    scheme = "basic"
)
public class QuarkusApplication extends Application {
}
