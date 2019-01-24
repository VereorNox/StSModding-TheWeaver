package weavermod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import weavermod.WeaverMod;
import weavermod.patches.AbstractCardEnum;

public class Restrain extends CustomCard {
    public static final String ID = weavermod.WeaverMod.makeID("Restrain");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = WeaverMod.makePath(WeaverMod.DEFAULT_UNCOMMON_SKILL);

    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = AbstractCardEnum.DEFAULT_GRAY;

    private static final int COST = 1;
    private static int BLOCK = 8;
    private static int BLOCK_MODIFIER = 5;
    private static int BLOCK_UPGRADE = 4;


    public Restrain() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseBlock = BLOCK;
        this.baseMagicNumber = this.magicNumber = BLOCK_MODIFIER;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(m != null && (m.intent == AbstractMonster.Intent.ATTACK || m.intent == AbstractMonster.Intent.ATTACK_BUFF || m.intent == AbstractMonster.Intent.ATTACK_DEBUFF || m.intent == AbstractMonster.Intent.ATTACK_DEFEND)) {
            if(m.hasPower(WeakPower.POWER_ID)) {
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block + magicNumber));
            } else {
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
            }
        } else {
            AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, EXTENDED_DESCRIPTION[0], true));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Restrain();
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.upgradeBlock(BLOCK_UPGRADE);
            initializeDescription();
        }
    }
}