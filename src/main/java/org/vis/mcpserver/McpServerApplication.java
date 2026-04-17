package org.vis.mcpserver;

import io.modelcontextprotocol.server.McpAsyncServer;
import io.modelcontextprotocol.server.McpServer;
import io.modelcontextprotocol.spec.McpStreamableServerTransportProvider;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.vis.mcpserver.tools.SellerAccountTools;

@SpringBootApplication
public class McpServerApplication {

    /**
     * Main Entry Point
     * Method Name: main
     * Parameters: String[] args - Command line arguments passed to the application
     *
     * Usage: Serves as the entry point for the Spring Boot application. This method
     * bootstraps the application context, initializes all Spring beans, starts the
     * embedded server, and loads all configurations from application.yml
     */
    public static void main(String[] args) {
        SpringApplication.run(McpServerApplication.class, args);
    }

    /**
     * Tool Callback Provider Configuration
     * Method Name: tools
     * Parameters: SellerAccountTools sellerAccountTools - The service containing all AI tool definitions
     * Return Type: ToolCallbackProvider - A provider that handles tool callbacks for the AI/LLM
     *
     * Usage: Creates a Spring Bean that registers and manages all seller account tools.
     * This provider exposes the seller account operations (search by name, search by owner, fetch all)
     * as callable AI tools that can be invoked via the MCP protocol.
     * The MethodToolCallbackProvider scans the SellerAccountTools service and registers
     * all methods annotated with @Tool annotation.
     */
    @Bean
    public ToolCallbackProvider tools(SellerAccountTools sellerAccountTools) {
        return MethodToolCallbackProvider.builder().toolObjects(sellerAccountTools).build();
    }

    /**
     * MCP Async Server Bean Configuration
     * Method Name: mcpAsyncServer
     * Parameters: McpStreamableServerTransportProvider transport - The HTTP transport provider for streaming MCP messages
     * Return Type: McpAsyncServer - An asynchronous MCP server instance
     *
     * Usage: Creates and configures the Model Context Protocol (MCP) async server bean.
     * This server handles all incoming MCP requests via the Streamable HTTP protocol.
     * The transport parameter is automatically provided by Spring AI framework as a
     * streamable HTTP implementation that listens on configured endpoints (/mcp, /mcp/message, /sse).
     * This enables bidirectional communication with MCP clients for tool invocation.
     */
    @Bean
    public McpAsyncServer mcpAsyncServer(McpStreamableServerTransportProvider transport) {
        // 'transport' is automatically provided as a Streamable-HTTP implementation
        return McpServer.async(transport).build();
    }

}
