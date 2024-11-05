package com.pnu.system.common.snapshot.provider.impl;

import com.pnu.system.common.rest.TimetableRestClient;
import com.pnu.system.common.snapshot.dto.UserSnapshotDto;
import com.pnu.system.common.snapshot.provider.UserSnapshotProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.util.UriTemplate;

import java.util.List;

@RequiredArgsConstructor
public class RestUserSnapshotProvider implements UserSnapshotProvider {

    private final TimetableRestClient restClient;

    @Value("${baseUrl.identity_access}/user/internal/snapshot/{id}")
    private String userSnapshotByIdUrl;
    @Value("${baseUrl.identity_access}/user/internal/snapshot/list")
    private String userSnapshotListUrl;

    @Override
    public UserSnapshotDto getById(String id) {
        String url = new UriTemplate(userSnapshotByIdUrl).expand(id).toString();
        return restClient.get(url, UserSnapshotDto.class);
    }

    @Override
    public List<UserSnapshotDto> getByIds(List<String> ids) {
        return restClient.postForList(userSnapshotListUrl, ids, UserSnapshotDto[].class);
    }
}
