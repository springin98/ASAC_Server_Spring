package com.example.demo.common;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public class BaseEntity {

    @CreationTimestamp
    @Column(name = "creatAt", nullable = false, updatable = false)
    private LocalDateTime creatAt;
    @UpdateTimestamp
    @Column(name = "updateAt", nullable = false)
    private LocalDateTime updateAt;
    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false, length = 10)
    protected State state = State.ACTIVE;
    public enum State {
        ACTIVE, INATIVE;
    }
}
