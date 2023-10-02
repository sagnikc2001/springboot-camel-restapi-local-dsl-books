package com.springcameldslbookslocal.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.springcameldslbookslocal.model.*;

@Component
public class BooksRouteBuilder extends RouteBuilder {
	
	@Autowired
	private Environment env;

	@Value("${camel.servlet.mapping.context-path}")
	private String contextPath;


	@Override
	public void configure() throws Exception {
		
		// this can also be configured in application.properties
		// Rest configuration
        restConfiguration()
//          This line sets the component for handling incoming REST requests to servlet
            .component("servlet")
            .bindingMode(RestBindingMode.json)
            .dataFormatProperty("prettyPrint", "true")
//          Cross-Origin Resource Sharing, allows requests from different origins to access resources on the server, which is useful for web applications running on different domains.
            .enableCORS(true)
            .port(env.getProperty("server.port", "8080"))
//          Context path for the REST service, represents the base URL path for the service
            .contextPath(contextPath.substring(0, contextPath.length() - 2))
            // turn on openapi api-doc
            .apiContextPath("/api-doc")
            .apiProperty("api.title", "Books API")
            .apiProperty("api.version", "1.0.0");
        
     // GET /books endpoint to retrieve all books
        rest("/books").description("User REST service")
        .consumes("application/json")
        .produces("application/json")
        
     // Define the GET operation to retrieve all books
        .get().description("Show all books").outType(Books.class)
        .responseMessage().code(200).message("All books successfully returned").endResponseMessage()
        .to("bean:bookService?method=getBooks");
        
     // GET /books/{isbn} endpoint to retrieve a book by ISBN
        rest("/books/{isbn}").description("Book by ISBN REST service")
        .consumes("application/json")
        .produces("application/json")
        
     // Define the GET operation to retrieve a book by ISBN
        .get().description("Find Book by isbn")
        .outType(Books.class)
        .param().name("isbn").description("The isbn of the user").dataType("integer").endParam()
        .responseMessage().code(200).message("Book successfully returned").endResponseMessage()
        .to("bean:bookService?method=getBook(${header.isbn})");
        
     // POST /books endpoint to create a new book
        rest("/books").description("Create Book REST service")
            .consumes("application/json")
            .produces("application/json")
        
     // Define the POST operation to create a new book
        .post().description("Create a new book").type(Books.class)
        .param().name("body").description("Book object to create").endParam()
        .responseMessage().code(200).message("Book successfully created").endResponseMessage()
        .to("direct:createBook");
        
//        PUT /books endpoint to update a book
//        rest("/books").description("Create Book REST service")
//        .consumes("application/json")
//        .produces("application/json")
        
     // CreateBook route
        from("direct:createBook")
            .bean("bookService", "addBook") // Call the addBook method to create a new book
            .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200)); // Set the HTTP response code to 200

	}

}
