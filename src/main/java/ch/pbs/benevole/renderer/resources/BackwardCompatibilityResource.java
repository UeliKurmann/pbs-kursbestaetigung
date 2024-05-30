package ch.pbs.benevole.renderer.resources;


import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/kurs/renderer")
@Produces(MediaType.APPLICATION_JSON)
@Deprecated
public class BackwardCompatibilityResource extends BenevoleRendererResource {

}