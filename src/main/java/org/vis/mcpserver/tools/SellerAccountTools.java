package org.vis.mcpserver.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;
import org.vis.mcpserver.entity.SellerAccount;
import org.vis.mcpserver.repository.SellerAccountRepository;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Service
public class SellerAccountTools {
    private final SellerAccountRepository sellerAccountRepository;
    // Inject ObjectMapper into your class
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Constructor - Dependency Injection
     * Method Name: SellerAccountTools
     * Parameters: SellerAccountRepository sellerAccountRepository - Repository for reactive database queries
     *
     * Usage: Initializes the SellerAccountTools service with the injected repository.
     * This constructor enables dependency injection of the SellerAccountRepository,
     * which provides access to database operations for seller account queries.
     */
    public SellerAccountTools(SellerAccountRepository sellerAccountRepository) {
        this.sellerAccountRepository = sellerAccountRepository;
    }

    /**
     * Search Seller Accounts by Name (MCP Tool)
     * Method Name: getAccountByName
     * Parameters: String name - The seller account name to search for (Tool Parameter)
     * Return Type: String - JSON string representation of matching seller accounts
     *
     * Tool Definition:
     * - Tool Name: search_seller_accounts_by_name
     * - Tool Description: Find all Seller Accounts by name
     *
     * Usage: This is an AI-callable tool exposed via the MCP protocol. When an AI/LLM
     * client invokes the "search_seller_accounts_by_name" tool with a name parameter,
     * this method executes a reactive query to find matching accounts. The results are
     * converted to JSON format for the AI to process. Used for intelligent seller lookups
     * based on business name. Returns JSON array of matching SellerAccount objects.
     *
     * Example: Searching for "harry" returns JSON of all accounts with that name
     */
    @Tool(name = "search_seller_accounts_by_name", description = "Find all Seller Accounts by name")
    public String getAccountByName(
            @ToolParam(description = "Seller Account Name") String name) {
       return objectMapper.writeValueAsString(sellerAccountRepository.findByName(name).collectList().block());
    }

    /**
     * Fetch All Seller Accounts (MCP Tool)
     * Method Name: getAllAccount
     * Parameters: None - This tool takes NO parameters
     * Return Type: List<SellerAccount> - List of seller accounts (limited to 5 most recent)
     *
     * Tool Definition:
     * - Tool Name: fetch_all_seller_accouts
     * - Tool Description: Fetches ALL seller accounts from the database. This tool takes NO parameters
     *
     * Usage: This is an AI-callable tool that retrieves all seller accounts from the database.
     * The results are limited to the first 5 records using .take(5) to prevent overwhelming
     * responses. This tool is useful when an AI needs a general overview of available sellers
     * without filtering by specific criteria. The reactive chain blocks at the end to convert
     * the asynchronous Flux stream to a synchronous List for the tool response.
     *
     * Note: Limited to 5 records for performance and response size optimization
     */
    @Tool(name = "fetch_all_seller_accouts", description = "Fetches ALL seller accounts from the database. This tool takes NO parameters")
    public List<SellerAccount> getAllAccount() {
        return sellerAccountRepository.findAll().take(5).collectList().block();
    }

    /**
     * Search Seller Accounts by Owner (MCP Tool)
     * Method Name: getAccountByOwner
     * Parameters: String owner - The owner/organization name to search for (Tool Parameter)
     * Return Type: String - JSON string representation of matching seller accounts
     *
     * Tool Definition:
     * - Tool Name: search_seller_accounts_by_owner
     * - Tool Description: Find all Seller Accounts by owner
     *
     * Usage: This is an AI-callable tool exposed via the MCP protocol. When an AI/LLM
     * client invokes the "search_seller_accounts_by_owner" tool with an owner parameter,
     * this method executes a reactive query to find all accounts belonging to that owner.
     * Results are converted to JSON format for AI processing. Useful for discovering all
     * sellers managed by a specific marketplace or organization (e.g., "amazon", "flipkart").
     * Returns JSON array of all SellerAccount objects for the specified owner.
     *
     * Example: Searching for owner "amazon" returns JSON of all Amazon-managed accounts
     */
    @Tool(name = "search_seller_accounts_by_owner", description = "Find all Seller Accounts by owner")
    public String getAccountByOwner(
            @ToolParam(description = "Seller Account Owner") String owner
    ) {
        return objectMapper.writeValueAsString(sellerAccountRepository.findByOwner(owner).collectList().block());
    }

}
