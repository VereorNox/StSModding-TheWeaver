package weavermod.relics.abstracts;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import weavermod.WeaverMod;

public abstract class WeaverRelic extends AbstractRelic
{
    public WeaverRelic(String setId, String imgName, RelicTier tier, LandingSound sfx)
    {
        super(setId, "", tier, sfx);

        imgUrl = imgName;

        if (img == null || outlineImg == null) {
            img = ImageMaster.loadImage(WeaverMod.assetPath("images/relics/" + imgName));
            largeImg = ImageMaster.loadImage(WeaverMod.assetPath("images/largeRelics/" + imgName));
            outlineImg = ImageMaster.loadImage(WeaverMod.assetPath("images/relics/outline/" + imgName));
        }
    }

    @Override
    public void loadLargeImg()
    {
        if (largeImg == null) {
            if (imgUrl.startsWith("test")) {
                largeImg = ImageMaster.loadImage(WeaverMod.assetPath("images/largeRelics/" + imgUrl));
            }
            if (largeImg == null) {
                largeImg = ImageMaster.loadImage(WeaverMod.assetPath("images/largeRelics/" + imgUrl));
            }
        }
    }

    public boolean deckDescriptionSearch(String[] terms)
    {
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            for(String s : terms) {
                if(c.rawDescription.toLowerCase().contains(s.toLowerCase())) {
                    return true;
                }
            }
        }
        return false;
    }

    public void onApplyPower(AbstractPower p, AbstractCreature target, AbstractCreature source) { }
}
