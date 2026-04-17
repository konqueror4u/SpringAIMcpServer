package org.vis.mcpserver.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.vis.mcpserver.entity.SellerAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface SellerAccountRepository extends ReactiveCrudRepository<SellerAccount, Long> {

    /**
     * Find Seller Accounts by Name (Reactive Query)
     * Method Name: findByName
     * Parameters: String name - The seller account name to search for (exact match)
     * Return Type: Flux<List<SellerAccount>> - Reactive stream of list containing matching seller accounts
     *
     * Usage: Performs an asynchronous, non-blocking database query to find all seller accounts
     * that match the given name. Returns a Flux (reactive stream) that emits a list of matching
     * SellerAccount objects. This method uses Spring Data R2DBC reactive repository pattern
     * for non-blocking database operations.
     *
     * Example: findByName("harry") returns all accounts named "harry"
     * The query is executed reactively and can be subscribed to for async processing.
     */
    Flux<List<SellerAccount>> findByName(String name);

    /**
     * Find Seller Accounts by Owner (Reactive Query)
     * Method Name: findByOwner
     * Parameters: String owner - The owner/organization name to search for
     * Return Type: Flux<List<SellerAccount>> - Reactive stream of list containing matching seller accounts
     *
     * Usage: Performs an asynchronous, non-blocking database query to find all seller accounts
     * that belong to a specific owner/organization. Returns a Flux (reactive stream) that emits
     * a list of all SellerAccount objects owned by the specified owner.
     * This is useful for querying all sellers managed by a particular marketplace or organization.
     *
     * Example: findByOwner("amazon") returns all accounts owned by Amazon
     * The query is executed reactively without blocking threads, enabling efficient
     * resource utilization in high-concurrency environments.
     */
    Flux<List<SellerAccount>> findByOwner(String owner);

}
