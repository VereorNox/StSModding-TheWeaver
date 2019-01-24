package weavermod.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.Dark;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import weavermod.WeaverMod;
import weavermod.relics.abstracts.WeaverRelic;

public class StarterRelic extends WeaverRelic {
    public static final String ID = WeaverMod.makeID("QueenAdministrator");

    private static final int SWARM_AMOUNT = 1;

    public StarterRelic() {
        super(ID, "QueenAdministrator.png", RelicTier.STARTER, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + SWARM_AMOUNT + DESCRIPTIONS[1];
    }

    @Override
    public void atBattleStart() {
        AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractDungeon.player.channelOrb(new Dark());
    }

    public AbstractRelic makeCopy() {
        return new StarterRelic();
    }
}
