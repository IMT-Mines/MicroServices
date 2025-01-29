package fr.imtmines.monsters.component;

import fr.imtmines.monsters.entity.Monster;
import fr.imtmines.monsters.repository.MonstersRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer {
    private final MonstersRepository monstersRepository;


    public DatabaseInitializer(MonstersRepository monstersRepository) {
        this.monstersRepository = monstersRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initializeDatabase() {
        monstersRepository.deleteAll();

        Monster zombie = new Monster();
        zombie.setName("zombie");
        zombie.setDamage(2);
        zombie.setGold(5);
        zombie.setItemDrop("peau de zombie");
        zombie.setMaxHealth(15);
        zombie.setImage("zombie.png");


        Monster zombiere = new Monster();
        zombiere.setName("zombiere");
        zombiere.setDamage(4);
        zombiere.setGold(10);
        zombiere.setItemDrop("peau de zombiere");
        zombiere.setMaxHealth(30);
        zombiere.setImage("zombiere.png");

        Monster squelette = new Monster();
        squelette.setName("squelette");
        squelette.setDamage(3);
        squelette.setGold(6);
        squelette.setItemDrop("os de squelette");
        squelette.setMaxHealth(21);
        squelette.setImage("squelette.png");

        Monster squelethanol = new Monster();
        squelethanol.setName("squelethanol");
        squelethanol.setDamage(6);
        squelethanol.setGold(12);
        squelethanol.setItemDrop("os de squelethanol");
        squelethanol.setMaxHealth(42);
        squelethanol.setImage("squelethanol.png");

        Monster chauvesouris = new Monster();
        chauvesouris.setName("chauves-souris");
        chauvesouris.setDamage(4);
        chauvesouris.setGold(7);
        chauvesouris.setItemDrop("aile de chauves-souris");
        chauvesouris.setMaxHealth(30);
        chauvesouris.setImage("chauvesouris.png");

        Monster chauvesouricard = new Monster();
        chauvesouricard.setName("chauves-souricard");
        chauvesouricard.setDamage(8);
        chauvesouricard.setGold(14);
        chauvesouricard.setItemDrop("aile de chauves-souricard");
        chauvesouricard.setMaxHealth(60);
        chauvesouricard.setImage("chauvesouricard.png");

        monstersRepository.save(zombie);
        monstersRepository.save(zombiere);
        monstersRepository.save(squelette);
        monstersRepository.save(squelethanol);
        monstersRepository.save(chauvesouris);
        monstersRepository.save(chauvesouricard);

    }
}