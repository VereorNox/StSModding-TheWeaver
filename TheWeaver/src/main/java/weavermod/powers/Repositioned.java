package weavermod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import weavermod.WeaverMod;

//Gain 1 dex for the turn for each card played.

public class Repositioned extends AbstractPower {
    public AbstractCreature source;

    public static final String POWER_ID = WeaverMod.makeID("Repositioned");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final String IMG = WeaverMod.makePath(WeaverMod.COMMON_POWER);
    public static boolean beenHurt;

    public Repositioned(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        type = PowerType.BUFF;
        isTurnBased = false;
        img = new Texture(IMG);
        this.source = source;
        this.beenHurt = false;
    }


    @Override
    public int onLoseHp(int damageAmount) {
        if ((AbstractDungeon.getCurrRoom().phase == com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase.COMBAT) && (damageAmount > 0)) {
            this.beenHurt = true;
        }
        return damageAmount;
    }

    // At the end of the turn, gain Dex if not hit.
    //TODO: For every stack of Repositioned
    @Override
    public void atEndOfTurn(final boolean isEnemy) {
        if(this.beenHurt == false) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, 3), amount));
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, "Repositioned"));
        }
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, "Repositioned"));

    }

    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        }

        else if (amount > 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        }
    }

}
