package com.madhax.website.repository;

import com.madhax.website.domain.DevNote;
import org.springframework.data.repository.CrudRepository;

public interface DevNoteRepository extends CrudRepository<DevNote, Long> {

}
