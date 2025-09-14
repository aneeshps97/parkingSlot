package com.example.parkingslot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class AutoAssignRequest {
    int parkingAreaId;
    List<Integer> UserIds;
    List<Integer> SlotIds;
    String startDate;
    String endDate;
    int frequency;
    @Override
    public String toString() {
        return "AutoAssignRequest{" +
                "parkingAreaId=" + parkingAreaId +
                ", UserIds=" + UserIds +
                ", SlotIds=" + SlotIds +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", frequency=" + frequency +
                '}';
    }

}
