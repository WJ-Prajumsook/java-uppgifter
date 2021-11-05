package org.wj.prajumsook.resource;

import jakarta.ws.rs.FormParam;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.wj.prajumsook.model.Account;
import org.wj.prajumsook.service.AccountService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/account")
public class AccountResource {

    @Inject
    private AccountService accountService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Get all accounts",
            description = "Retrieves and return all customer accounts"
    )
    @APIResponses(
            value = {
               @APIResponse (
                       responseCode = "200",
                       description = "Successfully retrieves account list",
                       content = @Content(
                               mediaType = MediaType.APPLICATION_JSON,
                               schema = @Schema(type = SchemaType.ARRAY, implementation = Account.class)
                       )
               ),
               @APIResponse (
                       responseCode = "400",
                       description = "Bad Request"
               ),
               @APIResponse (
                       responseCode = "500",
                       description = "Internal server error"
               )
            }
    )
    public Response getAccounts() {
        return Response.status(Response.Status.OK)
                .entity(accountService.findAll())
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{accountNumber}")
    @Operation(
            summary = "Get account by id",
            description = "Retrieves and return an customer account"
    )
    @APIResponses(
            value = {
                    @APIResponse (
                            responseCode = "200",
                            description = "Successfully retrieves account",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = Account.class)
                            )
                    ),
                    @APIResponse (
                            responseCode = "400",
                            description = "Bad Request"
                    ),
                    @APIResponse (
                            responseCode = "500",
                            description = "Internal server error"
                    )
            }
    )
    public Response getAccount(@PathParam("accountNumber")String accountNumber) {
        return Response.status(Response.Status.OK)
                .entity(accountService.findById(accountNumber))
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Create new account",
            description = "Create a new account"
    )
    @APIResponses(
            value = {
                    @APIResponse (
                            responseCode = "200",
                            description = "Successfully created account",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = Account.class)
                            )
                    ),
                    @APIResponse (
                            responseCode = "400",
                            description = "Bad Request"
                    ),
                    @APIResponse (
                            responseCode = "500",
                            description = "Internal server error"
                    )
            }
    )
    public Response createAccount(@FormParam("account")Account account) {
        return Response.status(Response.Status.OK)
                .entity(accountService.save(account))
                .build();
    }
}
