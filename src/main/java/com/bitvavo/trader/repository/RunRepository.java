package com.bitvavo.trader.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bitvavo.trader.model.Run;

@Repository
public interface RunRepository extends CrudRepository<Run, Long> {
    Run findFirstByOrderByTimeDesc();
    List<Run> findRunsByTimeIsAfterOrderByIdAsc(Long time);
}
