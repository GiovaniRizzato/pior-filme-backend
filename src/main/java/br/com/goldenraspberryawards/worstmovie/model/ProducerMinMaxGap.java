package br.com.goldenraspberryawards.worstmovie.model;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProducerMinMaxGap{
    private Collection<ProducerWinningGap> min;
    private Collection<ProducerWinningGap> max;
}