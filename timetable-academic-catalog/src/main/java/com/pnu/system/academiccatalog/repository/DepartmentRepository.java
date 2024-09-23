package com.pnu.system.academiccatalog.repository;

import com.pnu.system.academiccatalog.api.dto.DepartmentResponseDto;
import com.pnu.system.academiccatalog.domain.Department;
import com.pnu.system.academiccatalog.domain.QDepartment;
import com.pnu.system.common.dto.BaseSearchRequest;
import com.pnu.system.common.utils.QueryDslFactory;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {

    QDepartment qDepartment = QDepartment.department;

    default List<DepartmentResponseDto> getAll(BaseSearchRequest searchRequest) {
        return QueryDslFactory.getQueryFactory()
                .select(Projections.constructor(
                        DepartmentResponseDto.class,
                        qDepartment.id,
                        qDepartment.name,
                        qDepartment.shortName,
                        qDepartment.version
                ))
                .from(qDepartment)
                .limit(searchRequest.getLimit())
                .offset(searchRequest.getOffset())
                .fetch();
    }

}
