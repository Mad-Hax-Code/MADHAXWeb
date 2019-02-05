package com.madhax.website.repository;

import com.madhax.website.domain.DevMessage;
import org.springframework.data.repository.CrudRepository;

public interface DevMessageRepository extends CrudRepository<DevMessage, Long> {

}
