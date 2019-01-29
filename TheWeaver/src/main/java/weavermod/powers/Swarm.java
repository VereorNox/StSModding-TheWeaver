package weavermod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import weavermod.WeaverMod;

public class Swarm extends AbstractPower {

    public AbstractCreature source;

    public static final String POWER_ID = WeaverMod.makeID("Repositioned");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final String IMG = WeaverMod.makePath(WeaverMod.COMMON_POWER);
    private int damage;

    public Swarm() {

    }

    @Override
    public void atEndOfTurn(boolean isEnemy) {
        new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(this.damage, true, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE);

    }

}