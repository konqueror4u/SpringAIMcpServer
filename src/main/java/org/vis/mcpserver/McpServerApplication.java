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

    public static void main(String[] args) {
        SpringApplication.run(McpServerApplication.class, args);
    }

    @Bean
    public ToolCallbackProvider tools(SellerAccountTools sellerAccountTools) {
        return MethodToolCallbackProvider.builder().toolObjects(sellerAccountTools).build();
    }
    @Bean
    public McpAsyncServer mcpAsyncServer(McpStreamableServerTransportProvider transport) {
        // 'transport' is automatically provided as a Streamable-HTTP implementation
        return McpServer.async(transport).build();
    }

}
