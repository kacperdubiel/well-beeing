package com.wellbeeing.wellbeeing.domain.sport;


import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.account.TrainerProfile;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class TrainingPlanRequest {
    @Id
    @GeneratedValue
    @Column(name = "request_id")
    private Long id;

    @ManyToOne
    private Profile submitter;

    @ManyToOne
    private TrainerProfile trainer;

    @Column
    private String message;

    @Column
    private LocalDateTime beginningDate;

    @Column
    @CreationTimestamp
    private LocalDateTime requestDate = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    @Column
    private ERequestStatus requestStatus = ERequestStatus.SUBMITTED;

    public void setStatus(String status) {
        this.requestStatus = ERequestStatus.valueOf(status.toUpperCase());
    }
}
