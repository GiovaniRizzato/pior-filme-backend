package br.com.goldenraspberryawards.worstmovie.model.DTO;

import java.util.Collection;

import br.com.goldenraspberryawards.worstmovie.model.ProducerWinningGap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProducerMinMaxGap implements DTO{
    private Collection<ProducerWinningGap> min;
    private Collection<ProducerWinningGap> max;
}