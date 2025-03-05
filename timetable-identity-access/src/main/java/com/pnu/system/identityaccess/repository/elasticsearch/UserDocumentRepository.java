package com.pnu.system.identityaccess.repository.elasticsearch;

import com.pnu.system.elasticsearch.repository.ExtendedElasticsearchRepository;
import com.pnu.system.identityaccess.domain.document.UserDocument;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserDocumentRepository extends ExtendedElasticsearchRepository<UserDocument, UUID> {


}
