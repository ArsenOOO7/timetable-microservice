package com.pnu.system.common.snapshot.provider.impl;

import com.pnu.system.common.rest.TimetableRestClient;
import com.pnu.system.common.snapshot.dto.SubjectSnapshotDto;
import com.pnu.system.common.snapshot.provider.SubjectSnapshotProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.util.UriTemplate;

import java.util.List;

@RequiredArgsConstructor
public class RestSubjectSnapshotProvider implements SubjectSnapshotProvider {

    private final TimetableRestClient restClient;

    @Value("${baseUrl.academic_catalog}/subject/internal/snapshot/{id}")
    private String subjectSnapshotByIdUrl;
    @Value("${baseUrl.academic_catalog}/subject/internal/snapshot/list")
    private String subjectSnapshotListUrl;

    @Override
    public SubjectSnapshotDto getById(String id) {
        String url = new UriTemplate(subjectSnapshotByIdUrl).expand(id).toString();
        return restClient.get(url, SubjectSnapshotDto.class);
    }

    @Override
    public List<SubjectSnapshotDto> getByIds(List<String> ids) {
        return restClient.postForList(subjectSnapshotListUrl, ids, SubjectSnapshotDto[].class);
    }
}
