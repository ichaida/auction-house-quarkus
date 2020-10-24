package xyz.ichaida.services;

import xyz.ichaida.entities.Auction;
import xyz.ichaida.repositories.AuctionRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * @author Ismail Chaida.
 */
@ApplicationScoped
public class AuctionService {

    @Inject
    AuctionRepository auctionRepository;


    public List<Auction> getAll() {
        return auctionRepository.listAll();
    }

    public List<Auction> findByStatus(String status) {
        return auctionRepository.findByStatus(status);
    }

    public List<Auction> getAllByHouseName(String houseName) {
        return null;
    }
}
