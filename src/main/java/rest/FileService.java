package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import java.io.File;


@Path("/file")
public class FileService {

    public static final String FILE_PATH = "D:\\Java\\Web Services\\jetty-ws\\src\\main\\resources\\web.txt";

    @GET
    @Path("/get")
    @Produces("text/plain")
    public Response getFile() {

        File file = new File(FILE_PATH);


        ResponseBuilder builder = Response.ok(file);

        builder.header("Content-Disposition", "attachmnet; filename=\"file_from_server.log\"");

        return builder.build();


    }
}
