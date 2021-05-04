package Controller;

import Model.Card;
import Model.CardCategory;
import Model.CardType;

import java.util.ArrayList;
import java.util.List;

public class CardController {

    public static void initialCards() {
        Card monsterReborn = new Card("MonsterReborn", "nothing", 1,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.SPELL, 1, 0);
        Card commandKnight = new Card("CommandKnight", "Gain 400 ATK to all Warrior-Type monsters," +
                " if you control another monster, monsters your opponent " +
                "controls cannot target this card for an attack", 1,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.MONSTEREFFECT, 1, 0);
        Card battleOx = new Card("BattleOx", "nothing", 1,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.MONSTER, 1, 0);
        Card axeRaider = new Card("AxeRaider", "nothing", 1,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.MONSTER, 1, 0);
        Card hornImp = new Card("HornImp", "nothing", 1,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.MONSTER, 1, 0);
        Card yomiShip = new Card("YomiShip", "nothing", 1,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.MONSTEREFFECT, 1, 0);
    }


}
