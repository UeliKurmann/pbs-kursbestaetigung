package ch.pbs.benevole.renderer.resources;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/kurs/renderer")
@Produces(MediaType.APPLICATION_JSON)
@Deprecated
public class BackwardCompatibilityResource extends BenevoleRendererResource {

}