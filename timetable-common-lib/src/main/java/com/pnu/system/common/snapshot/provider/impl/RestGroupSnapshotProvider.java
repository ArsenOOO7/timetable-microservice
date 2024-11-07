package com.pnu.system.common.snapshot.provider.impl;

import com.pnu.system.common.rest.TimetableRestClient;
import com.pnu.system.common.snapshot.dto.GroupSnapshotDto;
import com.pnu.system.common.snapshot.provider.GroupSnapshotProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.util.UriTemplate;

import java.util.List;

@RequiredArgsConstructor
public class RestGroupSnapshotProvider implements GroupSnapshotProvider {

    private final TimetableRestClient restClient;

    @Value("${baseUrl.group}/group/internal/snapshot/{id}")
    private String groupSnapshotByIdUrl;
    @Value("${baseUrl.group}/group/internal/snapshot/list")
    private String groupSnapshotListUrl;

    @Override
    public GroupSnapshotDto getById(String id) {
        String url = new UriTemplate(groupSnapshotByIdUrl).expand(id).toString();
        return restClient.get(url, GroupSnapshotDto.class);
    }

    @Override
    public List<GroupSnapshotDto> getByIds(List<String> ids) {
        return restClient.postForList(groupSnapshotListUrl, ids, GroupSnapshotDto[].class);
    }
}
