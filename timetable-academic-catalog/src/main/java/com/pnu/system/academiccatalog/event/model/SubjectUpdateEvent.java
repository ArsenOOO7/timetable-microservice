package com.pnu.system.academiccatalog.event.model;

import com.pnu.system.academiccatalog.domain.Subject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SubjectUpdateEvent {

    private Subject subject;

}
