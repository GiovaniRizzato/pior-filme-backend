package br.com.goldenraspberryawards.worstmovie.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProducerWinningGap{
    private String producer;
    private int interval;
    private int previousWin;
    private int followingWin;
}