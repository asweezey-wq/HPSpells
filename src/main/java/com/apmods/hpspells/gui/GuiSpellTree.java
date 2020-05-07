package com.apmods.hpspells.gui;

import java.util.List;
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.apmods.hpspells.extendedplayer.SpellSkills;
import com.apmods.hpspells.network.HPNetwork;
import com.apmods.hpspells.network.LevelUpKnowledgePacket;
import com.apmods.hpspells.spell.EnumSpellType;
import com.apmods.hpspells.spell.ISpell;
import com.apmods.hpspells.spell.SpellDiffindo;
import com.apmods.hpspells.spell.SpellObscuro;
import com.apmods.hpspells.spell.SpellTree;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.IProgressMeter;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class GuiSpellTree extends GuiScreen implements IProgressMeter {
	private static final int field_146572_y = AchievementList.minDisplayColumn * 24 - 112;
	private static final int field_146571_z = AchievementList.minDisplayRow * 24 - 112;
	private static final int field_146559_A = AchievementList.maxDisplayColumn * 24 - 77;
	private static final int field_146560_B = AchievementList.maxDisplayRow * 24 - 77;
	private static final ResourceLocation field_146561_C = new ResourceLocation("textures/gui/achievement/achievement_background.png");
	protected GuiScreen field_146562_a;
	protected int field_146555_f = 256;
	protected int field_146557_g = 202;
	protected int field_146563_h;
	protected int field_146564_i;
	protected float field_146570_r = 1.0F;
	protected double field_146569_s;
	protected double field_146568_t;
	protected double field_146567_u;
	protected double field_146566_v;
	protected double field_146565_w;
	protected double field_146573_x;
	private int field_146554_D;
	private StatFileWriter field_146556_E;
	private boolean field_146558_F = true;
	private static final String __OBFID = "CL_00000722";

	private int currentPage = -1;

	public GuiSpellTree(GuiScreen p_i45026_1_, StatFileWriter p_i45026_2_) {
		this.field_146562_a = p_i45026_1_;
		this.field_146556_E = p_i45026_2_;
		short short1 = 0;
		short short2 = 0;
		this.field_146569_s = this.field_146567_u = this.field_146565_w = (double) (AchievementList.openInventory.displayColumn * 24 - short1 / 2 - 12);
		this.field_146568_t = this.field_146566_v = this.field_146573_x = (double) (AchievementList.openInventory.displayRow * 24 - short2 / 2);
	}

	/**
	 * Adds the buttons (and other controls) to the screen in question.
	 */
	public void initGui() {
		SpellTree.loadSpells();
		this.mc.getNetHandler().addToSendQueue(new C16PacketClientStatus(C16PacketClientStatus.EnumState.REQUEST_STATS));
		this.buttonList.clear();
	}

	/**
	 * Fired when a key is typed. This is the equivalent of
	 * KeyListener.keyTyped(KeyEvent e).
	 */
	protected void keyTyped(char p_73869_1_, int p_73869_2_) {
		if (p_73869_2_ == this.mc.gameSettings.keyBindInventory.getKeyCode()) {
			this.mc.displayGuiScreen((GuiScreen) null);
			this.mc.setIngameFocus();
		} else {
			super.keyTyped(p_73869_1_, p_73869_2_);
		}
	}

	/**
	 * Draws the screen and all the components in it.
	 */
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
		int k;

		if (Mouse.isButtonDown(0)) {
			k = (this.width - this.field_146555_f) / 2;
			int l = (this.height - this.field_146557_g) / 2;
			int i1 = k + 8;
			int j1 = l + 17;

			if ((this.field_146554_D == 0 || this.field_146554_D == 1) && p_73863_1_ >= i1 && p_73863_1_ < i1 + 224 && p_73863_2_ >= j1 && p_73863_2_ < j1 + 155) {
				if (this.field_146554_D == 0) {
					this.field_146554_D = 1;
				} else {
					this.field_146567_u -= (double) ((float) (p_73863_1_ - this.field_146563_h) * this.field_146570_r);
					this.field_146566_v -= (double) ((float) (p_73863_2_ - this.field_146564_i) * this.field_146570_r);
					this.field_146565_w = this.field_146569_s = this.field_146567_u;
					this.field_146573_x = this.field_146568_t = this.field_146566_v;
				}

				this.field_146563_h = p_73863_1_;
				this.field_146564_i = p_73863_2_;
			}
		} else {
			this.field_146554_D = 0;
		}

		k = Mouse.getDWheel();
		float f4 = this.field_146570_r;

		if (k < 0) {
			this.field_146570_r += 0.25F;
		} else if (k > 0) {
			this.field_146570_r -= 0.25F;
		}

		this.field_146570_r = MathHelper.clamp_float(this.field_146570_r, 1.0F, 2.0F);

		if (this.field_146570_r != f4) {
			float f6 = f4 - this.field_146570_r;
			float f5 = f4 * (float) this.field_146555_f;
			float f1 = f4 * (float) this.field_146557_g;
			float f2 = this.field_146570_r * (float) this.field_146555_f;
			float f3 = this.field_146570_r * (float) this.field_146557_g;
			this.field_146567_u -= (double) ((f2 - f5) * 0.5F);
			this.field_146566_v -= (double) ((f3 - f1) * 0.5F);
			this.field_146565_w = this.field_146569_s = this.field_146567_u;
			this.field_146573_x = this.field_146568_t = this.field_146566_v;
		}

		if (this.field_146565_w < (double) field_146572_y) {
			this.field_146565_w = (double) field_146572_y;
		}

		if (this.field_146573_x < (double) field_146571_z) {
			this.field_146573_x = (double) field_146571_z;
		}

		if (this.field_146565_w >= (double) field_146559_A) {
			this.field_146565_w = (double) (field_146559_A - 1);
		}

		if (this.field_146573_x >= (double) field_146560_B) {
			this.field_146573_x = (double) (field_146560_B - 1);
		}

		this.drawDefaultBackground();
		this.func_146552_b(p_73863_1_, p_73863_2_, p_73863_3_);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		this.func_146553_h();
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
	}

	public void func_146509_g() {
		if (this.field_146558_F) {
			this.field_146558_F = false;
		}
	}

	/**
	 * Called from the main game loop to update the screen.
	 */
	public void updateScreen() {
		if (!this.field_146558_F) {
			this.field_146569_s = this.field_146567_u;
			this.field_146568_t = this.field_146566_v;
			double d0 = this.field_146565_w - this.field_146567_u;
			double d1 = this.field_146573_x - this.field_146566_v;

			if (d0 * d0 + d1 * d1 < 4.0D) {
				this.field_146567_u += d0;
				this.field_146566_v += d1;
			} else {
				this.field_146567_u += d0 * 0.85D;
				this.field_146566_v += d1 * 0.85D;
			}
		}
	}

	protected void func_146553_h() {
		int i = (this.width - this.field_146555_f) / 2;
		int j = (this.height - this.field_146557_g) / 2;
		this.fontRendererObj.drawString(I18n.format("HP Spells", new Object[0]), i + 15, j + 5, 4210752);
	}

	protected void func_146552_b(int p_146552_1_, int p_146552_2_, float hi) {
		int k = MathHelper.floor_double(this.field_146569_s + (this.field_146567_u - this.field_146569_s) * (double) 1);
		int l = MathHelper.floor_double(this.field_146568_t + (this.field_146566_v - this.field_146568_t) * (double) 1);

		if (k < field_146572_y) {
			k = field_146572_y;
		}

		if (l < field_146571_z) {
			l = field_146571_z;
		}

		if (k >= field_146559_A) {
			k = field_146559_A - 1;
		}

		if (l >= field_146560_B) {
			l = field_146560_B - 1;
		}

		int i1 = (this.width - this.field_146555_f) / 2;
		int j1 = (this.height - this.field_146557_g) / 2;
		int k1 = i1 + 16;
		int l1 = j1 + 17;
		this.zLevel = 0.0F;
		GL11.glDepthFunc(GL11.GL_GEQUAL);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) k1, (float) l1, -200.0F);
		// FIXES models rendering weirdly in the acheivements pane
		// see
		// https://github.com/MinecraftForge/MinecraftForge/commit/1b7ce7592caafb760ec93066184182ae0711e793#commitcomment-10512284
		GL11.glScalef(1.0F / this.field_146570_r, 1.0F / this.field_146570_r, 1.0F);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		int i2 = k + 288 >> 4;
		int j2 = l + 288 >> 4;
		int k2 = (k + 288) % 16;
		int l2 = (l + 288) % 16;
		boolean flag = true;
		boolean flag1 = true;
		boolean flag2 = true;
		boolean flag3 = true;
		boolean flag4 = true;
		Random random = new Random();
		float f1 = 16.0F / this.field_146570_r;
		float f2 = 16.0F / this.field_146570_r;
		int i3;
		int j3 = 0;
		int k3 = 0;

		for (i3 = 0; (float) i3 * f1 - (float) l2 < 155.0F; ++i3) {
			float f3 = 0.6F - (float) (j2 + i3) / 25.0F * 0.3F;
			f3 = 0.75f;
			GL11.glColor4f(f3, f3, f3, 1.0F);

			for (j3 = 0; (float) j3 * f2 - (float) k2 < 224.0F; ++j3) {
				random.setSeed((long) (this.mc.getSession().getPlayerID().hashCode() + i2 + j3 + (j2 + i3) * 16));
				k3 = random.nextInt(1 + j2 + i3) + (j2 + i3) / 2;
				IIcon iicon = Blocks.sand.getIcon(0, 0);

				if (k3 <= 37 && j2 + i3 != 35) {
					if (k3 == 22) {
						if (random.nextInt(2) == 0) {
							iicon = Blocks.diamond_ore.getIcon(0, 0);
						} else {
							iicon = Blocks.redstone_ore.getIcon(0, 0);
						}
					} else if (k3 == 10) {
						iicon = Blocks.iron_ore.getIcon(0, 0);
					} else if (k3 == 8) {
						iicon = Blocks.coal_ore.getIcon(0, 0);
					} else if (k3 > 4) {
						iicon = Blocks.stone.getIcon(0, 0);
					} else if (k3 > 0) {
						iicon = Blocks.dirt.getIcon(0, 0);
					}
				} else {
					iicon = Blocks.bedrock.getIcon(0, 0);
				}
				iicon = Blocks.portal.getIcon(0, 0);
				this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
				this.drawTexturedModelRectFromIcon(j3 * 16 - k2, i3 * 16 - l2, iicon, 16, 16);
			}
		}

		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthFunc(GL11.GL_LEQUAL);
		this.mc.getTextureManager().bindTexture(field_146561_C);
		SpellSkills ext = SpellSkills.get(this.mc.thePlayer);

		int i4;
		int j4 = 0;
		int l4 = 0;

		float f4 = (float) (p_146552_1_ - k1) * this.field_146570_r;
		float f5 = (float) (p_146552_2_ - l1) * this.field_146570_r;
		RenderHelper.enableGUIStandardItemLighting();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		int i5 = 0;
		int j5 = 0;

		ISpell spell1 = null;
		int column;
		int row;
		DrawSpellListParameter params = new DrawSpellListParameter(k, l, j3, k3, j4, l4, i5, j5, f4, f5, ext);

		int start = -10;
		for (Object o : SpellTree.spellTree) {
			ISpell spell2;
			if (o instanceof List) {
				if ((spell2 = this.drawSpellList((List) o, start, -2, params)) != null) {
					spell1 = spell2;
				}
				start += (1 + SpellTree.countLists((List) o)) * 2;
			}
		}

		k = params.k;
		l = params.l;
		j3 = params.j3;
		k3 = params.k3;
		j4 = params.j4;
		l4 = params.l4;
		i5 = params.i5;
		j5 = params.j5;
		f4 = params.f4;
		f5 = params.f5;

		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glPopMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(field_146561_C);
		this.drawTexturedModalRect(i1, j1, 0, 0, this.field_146555_f, this.field_146557_g);
		this.zLevel = 0.0F;
		GL11.glDepthFunc(GL11.GL_LEQUAL);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		super.drawScreen(p_146552_1_, p_146552_2_, hi);

		i5 = p_146552_1_ + 12;
		j5 = p_146552_2_ - 4;
		if (spell1 != null) {
			String s1 = spell1.getName();
			String s2 = I18n.format("spell." + spell1.getName().toLowerCase() + ".desc", new Object[] {});
			j4 = Math.max(this.fontRendererObj.getStringWidth(s1), 120);
			int k5 = this.fontRendererObj.splitStringWidth(s2, j4) + 20;

			int requiredExp = 10 + (spell1.isComplicated() ? 5 : 0);
			this.drawGradientRect(i5 - 3, j5 - 3, i5 + j4 + 3, j5 + k5 + 3 + 16 + (ext.knowsSpell(spell1) ? 12 : 0), -1073741824, -1073741824);
			this.fontRendererObj.drawStringWithShadow(s1, i5, j5, ext.knowsSpell(spell1) ? (spell1.isComplicated() ? -128 : -1) : (spell1.isComplicated() ? -8355776 : -8355712));
			this.fontRendererObj.drawSplitString(s2, i5, j5 + 12, j4, -6250336);
			if (ext.knowsSpell(spell1)) {
				this.fontRendererObj.drawStringWithShadow("Learned!", i5, j5 + k5, -7302913);
				this.fontRendererObj.drawStringWithShadow("Knowledge Lvl: " + ext.getKnowledgeLevel(spell1), i5, j5 + k5 + 10, -7302913);
				this.fontRendererObj.drawStringWithShadow("Skill Level: " + ext.getCurrentSkillLevel(spell1), i5, j5 + k5 + 20, 0xffff55);
			} else {
				this.fontRendererObj.drawSplitString("Requires " + requiredExp + " XP Levels to learn!", i5, j5 + k5, j4, this.mc.thePlayer.experienceLevel >= requiredExp ? 0x22ff22 : 0xff5555);
			}
		}
	}

	private ISpell drawSpellList(List list, int row, int column, DrawSpellListParameter parameterObject) {
		ISpell toReturn = null;
		int k = parameterObject.k;
		int l = parameterObject.l;
		int j5 = parameterObject.j5;
		int i5 = parameterObject.i5;
		int l4 = parameterObject.l4;
		int j4 = parameterObject.j4;
		int k3 = parameterObject.k3;
		int j3 = parameterObject.j3;
		float f4 = parameterObject.f4;
		float f5 = parameterObject.f5;
		for (Object o : list) {
			if (o instanceof ISpell) {
				int distBetweenRows = 20;
				ISpell spell = (ISpell) o;
				int indexOf = SpellTree.indexOfWithoutLists(o, list);
				int indexOfChild = 0;
				ISpell childSpell = null;
				List listWOLists = SpellTree.listWithoutLists(list);
				if (indexOf < listWOLists.size() - 1) {
					Object o1 = listWOLists.get(indexOf + 1);
					if (!(o1 instanceof ISpell) && indexOf < listWOLists.size() - 2) {
						o1 = listWOLists.get(indexOf + 2);
					}
					int indexOf2 = list.indexOf(spell);
					if (list.get(indexOf2 + 1) instanceof List) {
						indexOfChild = indexOf;
						childSpell = (ISpell) ((List) list.get(indexOf2 + 1)).get(0);
						j3 = ((indexOfChild + column) * 3) * 24 - k + 11;
						k3 = (row + (SpellTree.indexOfList((List) list.get(indexOf2 + 1), list) * 2)) * distBetweenRows - l + 11;
						l4 = ((indexOf + column) * 3) * 24 - k + 11;
						int l3 = row * distBetweenRows - l + 11;
						boolean flag5 = parameterObject.ext.knowsSpell(spell);
						boolean flag6 = childSpell != null ? parameterObject.ext.knowsSpell(childSpell) : false;
						j4 = -16777216;

						if (flag5) {
							j4 = -6250336;
						} else if (flag6) {
							j4 = -16711936;
						}

						this.drawHorizontalLine(j3, l4, k3, j4);
						this.drawVerticalLine(l4, k3, l3, j4);

						if (j3 > l4) {
							this.drawTexturedModalRect(j3 - 11 - 7, k3 - 5, 114, 234, 7, 11);
						} else if (j3 < l4) {
							this.drawTexturedModalRect(j3 + 11, k3 - 5, 107, 234, 7, 11);
						} else if (k3 > l3) {
							this.drawTexturedModalRect(j3 - 5, k3 - 11 - 7, 96, 234, 11, 7);
						} else if (k3 < l3) {
							this.drawTexturedModalRect(j3 - 5, k3 + 11, 96, 241, 11, 7);
						}
					}
					indexOf = SpellTree.indexOfWithoutLists(o, list);
					indexOfChild = SpellTree.indexOfWithoutLists(o1, list);
					childSpell = (ISpell) o1;
				}
				if (indexOfChild > 0) {
					j3 = ((indexOfChild + column) * 3) * 24 - k + 11;
					k3 = (row) * distBetweenRows - l + 11;
					l4 = ((indexOf + column) * 3) * 24 - k + 11;
					int l3 = (row) * distBetweenRows - l + 11;
					boolean flag5 = parameterObject.ext.knowsSpell(spell);
					boolean flag6 = childSpell != null ? parameterObject.ext.knowsSpell(childSpell) : false;
					j4 = -16777216;

					if (flag5) {
						j4 = -6250336;
					} else if (flag6) {
						j4 = -16711936;
					}

					this.drawHorizontalLine(j3, l4, k3, j4);
					this.drawVerticalLine(l4, k3, l3, j4);

					if (j3 > l4) {
						this.drawTexturedModalRect(j3 - 11 - 7, k3 - 5, 114, 234, 7, 11);
					} else if (j3 < l4) {
						this.drawTexturedModalRect(j3 + 11, k3 - 5, 107, 234, 7, 11);
					} else if (k3 > l3) {
						this.drawTexturedModalRect(j3 - 5, k3 - 11 - 7, 96, 234, 11, 7);
					} else if (k3 < l3) {
						this.drawTexturedModalRect(j3 - 5, k3 + 11, 96, 241, 11, 7);
					}
				}
				i5 = ((indexOf + column) * 3) * 24 - k;
				j5 = row * distBetweenRows - l;
				if (i5 >= -24 && j5 >= -24 && (float) i5 <= 224.0F * this.field_146570_r && (float) j5 <= 155.0F * this.field_146570_r) {
					float f6;
					if (spell.getSpellType() == EnumSpellType.DARK) {
						float base = 0.4f;
						GL11.glColor4f(base + parameterObject.ext.getKnowledgeLevel(spell) * 0.2f, 0, 0, 1);
					} else if (parameterObject.ext.knowsSpell(spell)) {
						float base = 0.4f - (spell.isComplicated() ? 0.2f : 0);
						float knowledge = (parameterObject.ext.getKnowledgeLevel(spell) * 0.2f);
						f6 = base + knowledge;

						GL11.glColor4f(f6, f6, f6, 1);
					} else if (childSpell != null && parameterObject.ext.knowsSpell(childSpell)) {
						f6 = 0.2f;
						GL11.glColor4f(f6, f6, f6, 1);
					} else {
						f6 = 0.1f;
						GL11.glColor4f(f6, f6, f6, 1);
					}
					this.mc.getTextureManager().bindTexture(field_146561_C);

					GL11.glEnable(GL11.GL_BLEND);
					if (spell.isComplicated()) {
						this.drawTexturedModalRect(i5 - 2, j5 - 2, 26, 202, 26, 26);
					} else {
						this.drawTexturedModalRect(i5 - 2, j5 - 2, 0, 202, 26, 26);
					}
					GL11.glDisable(GL11.GL_BLEND);
					if (f4 >= (float) i5 && f4 <= (float) (i5 + 22) && f5 >= (float) j5 && f5 <= (float) (j5 + 22)) {
						if(spell instanceof SpellObscuro || spell instanceof SpellDiffindo){
							toReturn = spell;
						}else{
							toReturn = spell;
						}
					}
				}
			} else if (o instanceof List && !((List) o).isEmpty()) {
				DrawSpellListParameter params = new DrawSpellListParameter(k, l, i5, j5, j3, k3, j4, l4, f4, f5, parameterObject.ext);
				int subColumn = column + (SpellTree.indexOfWithoutLists((List) o, list)) - 1;
				ISpell spell1 = drawSpellList((List) o, row + (SpellTree.indexOfList((List) o, list) * 2), subColumn, params);
				if (toReturn == null) {
					toReturn = spell1;
				}
			}
		}
		return toReturn;
	}

	@Override
	protected void mouseClicked(int x, int y, int p_73864_3_) {
		super.mouseClicked(x, y, p_73864_3_);
		SpellSkills ext = SpellSkills.get(this.mc.thePlayer);
		checkForInRect(SpellTree.spellTree, null, x, y, -10, -2);
	}

	private void checkForInRect(List list, List superList, int x, int y, int row, int column) {
		SpellSkills ext = SpellSkills.get(this.mc.thePlayer);
		for (int i = 0; i < list.size(); i++) {
			Object o = list.get(i);
			if (o instanceof ISpell) {
				ISpell spell = (ISpell) o;
				if (inRect(list, spell, row, column, x, y, 1f)) {
					ISpell parentSpell = null;
					if (i > 0) {
						if (list.get(i - 1) instanceof ISpell) {
							parentSpell = (ISpell) list.get(i - 1);
						} else if (list.get(i - 2) instanceof ISpell) {
							parentSpell = (ISpell) list.get(i - 2);
						}
					} else if (superList != null && superList != SpellTree.spellTree) {
						int j = superList.indexOf(list);
						if (superList.get(j - 1) instanceof ISpell) {
							parentSpell = (ISpell) superList.get(j - 1);
						}
					}
					if (parentSpell == null || ext.knowsSpell(parentSpell)) {
						HPNetwork.net.sendToServer(new LevelUpKnowledgePacket.KnowledgeMessage(spell));
					}
				}
			} else if (o instanceof List) {
				if (superList == null) {
					checkForInRect((List) o, list, x, y, row, column);
					row += (1 + SpellTree.countLists((List) o)) * 2;
				} else {
					int subColumn = column + (SpellTree.indexOfWithoutLists((List) o, list)) - 1;
					checkForInRect((List) o, list, x, y, row + (SpellTree.indexOfList((List) o, list) * 2), subColumn);
				}
			}
		}
	}

	private boolean inRect(List list, ISpell rect, int row, int column, int x, int y, float p_73864_3_) {
		int k = MathHelper.floor_double(this.field_146569_s + (this.field_146567_u - this.field_146569_s) * (double) p_73864_3_);
		int l = MathHelper.floor_double(this.field_146568_t + (this.field_146566_v - this.field_146568_t) * (double) p_73864_3_);

		if (k < field_146572_y) {
			k = field_146572_y;
		}

		if (l < field_146571_z) {
			l = field_146571_z;
		}

		if (k >= field_146559_A) {
			k = field_146559_A - 1;
		}

		if (l >= field_146560_B) {
			l = field_146560_B - 1;
		}
		int i1 = (this.width - this.field_146555_f) / 2;
		int j1 = (this.height - this.field_146557_g) / 2;
		int k1 = i1 + 16;
		int l1 = j1 + 17;
		float f4 = (float) (x - k1) * this.field_146570_r;
		float f5 = (float) (y - l1) * this.field_146570_r;
		int distBetweenRows = 20;
		int indexOf = SpellTree.indexOfWithoutLists(rect, list);
		int i5 = ((indexOf + column) * 3) * 24 - k;
		int j5 = row * distBetweenRows - l;
		if(rect instanceof SpellObscuro || rect instanceof SpellDiffindo){
			if (f4 >= (float) i5 && f4 <= (float) (i5 + 22) && f5 >= (float) j5 && f5 <= (float) (j5 + 22)) {
				return true;
			}
		}
		if (f4 >= (float) i5 && f4 <= (float) (i5 + 22) && f5 >= (float) j5 && f5 <= (float) (j5 + 22)) {
			return true;
		}
		return false;
	}

	/**
	 * Returns true if this GUI should pause the game when it is displayed in
	 * single-player
	 */
	public boolean doesGuiPauseGame() {
		return false;
	}

}