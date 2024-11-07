package com.pnu.system.common.snapshot.provider.impl;

import com.pnu.system.common.rest.TimetableRestClient;
import com.pnu.system.common.snapshot.dto.LessonLocationSnapshotDto;
import com.pnu.system.common.snapshot.provider.LessonLocationSnapshotProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.util.UriTemplate;

import java.util.List;

@RequiredArgsConstructor
public class RestLessonLocationSnapshotProvider implements LessonLocationSnapshotProvider {

    private final TimetableRestClient restClient;

    @Value("${baseUrl.lesson_location}/location/internal/snapshot/{id}")
    private String locationSnapshotByIdUrl;
    @Value("${baseUrl.lesson_location}/location/internal/snapshot/list")
    private String locationSnapshotListUrl;

    @Override
    public LessonLocationSnapshotDto getById(String id) {
        String url = new UriTemplate(locationSnapshotByIdUrl).expand(id).toString();
        return restClient.get(url, LessonLocationSnapshotDto.class);
    }

    @Override
    public List<LessonLocationSnapshotDto> getByIds(List<String> ids) {
        return restClient.postForList(locationSnapshotListUrl, ids, LessonLocationSnapshotDto[].class);
    }
}
