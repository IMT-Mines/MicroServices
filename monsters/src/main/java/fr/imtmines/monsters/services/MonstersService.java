package fr.imtmines.monsters.services;

import fr.imtmines.monsters.entity.Monster;
import fr.imtmines.monsters.repository.MonstersRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class MonstersService {

    private final MonstersRepository monstersRepository;

    public MonstersService(MonstersRepository monstersRepository) {
        this.monstersRepository = monstersRepository;
    }

    public Monster getMonstersById(Long id) {
        return monstersRepository.findById(id).orElse(null);
    }

}

