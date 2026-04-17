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
    public SellerAccountTools(SellerAccountRepository sellerAccountRepository) {
        this.sellerAccountRepository = sellerAccountRepository;
    }

    /**
     * Search all seller accounts by name
     * @param name Seller Account Name
     * @return List of Seller Accounts
     */
    @Tool(name = "search_seller_accounts_by_name", description = "Find all Seller Accounts by name")
    public String getAccountByName(
            @ToolParam(description = "Seller Account Name") String name) {
       return objectMapper.writeValueAsString(sellerAccountRepository.findByName(name).collectList().block());
    }
    @Tool(name = "fetch_all_seller_accouts", description = "Fetches ALL seller accounts from the database. This tool takes NO parameters")
    public List<SellerAccount> getAllAccount() {
        return sellerAccountRepository.findAll().take(5).collectList().block();
    }

    /**
     * Search all seller accounts by owner
     * @param owner Seller Account By Owner
     * @return List of Seller Accounts
     */
    @Tool(name = "search_seller_accounts_by_owner", description = "Find all Seller Accounts by owner")
    public String getAccountByOwner(
            @ToolParam(description = "Seller Account Owner") String owner
    ) {
        return objectMapper.writeValueAsString(sellerAccountRepository.findByOwner(owner).collectList().block());
    }

}
