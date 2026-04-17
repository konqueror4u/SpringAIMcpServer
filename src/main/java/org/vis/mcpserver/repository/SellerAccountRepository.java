package org.vis.mcpserver.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.vis.mcpserver.entity.SellerAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface SellerAccountRepository extends ReactiveCrudRepository<SellerAccount, Long> {
    /**
     * Search all seller accounts by name
     * @param name Seller Account Name
     * @return List of Seller Account
     */
    Flux<List<SellerAccount>> findByName(String name);

    /**
     * Search all seller accounts by owner
     * @param owner Seller account owner
     * @return Lst of Seller Accounts
     */
    Flux<List<SellerAccount>> findByOwner(String owner);

}
