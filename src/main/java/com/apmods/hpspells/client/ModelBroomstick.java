package com.apmods.hpspells.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Broomstick - APmodS
 * Created using Tabula 5.1.0
 */
public class ModelBroomstick extends ModelBase {
    public ModelRenderer handle;
    public ModelRenderer stick1;
    public ModelRenderer stick2;
    public ModelRenderer stick3;
    public ModelRenderer stick4;
    public ModelRenderer stick5;
    public ModelRenderer stick1_1;
    public ModelRenderer stick2_1;
    public ModelRenderer stick3_1;
    public ModelRenderer stick4_1;
    public ModelRenderer stick1_2;
    public ModelRenderer stick2_2;
    public ModelRenderer stick3_2;
    public ModelRenderer stick4_2;

    public ModelBroomstick() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.stick1_2 = new ModelRenderer(this, 0, 0);
        this.stick1_2.setRotationPoint(0.0F, 0.0F, -3.0F);
        this.stick1_2.addBox(0.0F, 0.0F, -8.0F, 1, 1, 8, 0.0F);
        this.setRotateAngle(stick1_2, -0.18203784098300857F, -0.4553564018453205F, 0.0F);
        this.stick3_1 = new ModelRenderer(this, 0, 0);
        this.stick3_1.setRotationPoint(0.0F, 0.0F, -3.0F);
        this.stick3_1.addBox(0.0F, 0.0F, -8.0F, 1, 1, 8, 0.0F);
        this.setRotateAngle(stick3_1, 0.47647488579445196F, -0.21589722847169854F, 0.0F);
        this.stick2 = new ModelRenderer(this, 0, 10);
        this.stick2.setRotationPoint(0.0F, 0.0F, -3.0F);
        this.stick2.addBox(0.0F, 0.0F, -9.0F, 1, 1, 9, 0.0F);
        this.setRotateAngle(stick2, 0.091106186954104F, 0.47472955654245763F, -0.045553093477052F);
        this.stick3_2 = new ModelRenderer(this, 0, 0);
        this.stick3_2.setRotationPoint(0.0F, 0.0F, -3.0F);
        this.stick3_2.addBox(0.0F, 0.0F, -8.0F, 1, 1, 8, 0.0F);
        this.setRotateAngle(stick3_2, 18.640507030329186F, -0.2159238339928044F, -44.07392693598679F);
        this.handle = new ModelRenderer(this, 0, 0);
        this.handle.setRotationPoint(0.0F, 0.0F, 17.0F);
        this.handle.addBox(0.0F, 0.0F, -21.0F, 1, 1, 22, 0.0F);
        this.stick5 = new ModelRenderer(this, 0, 10);
        this.stick5.setRotationPoint(0.0F, 0.0F, -3.0F);
        this.stick5.addBox(0.0F, 0.0F, -9.7F, 1, 1, 9, 0.0F);
        this.setRotateAngle(stick5, -0.045553093477052F, -0.045553093477052F, 0.40980330836826856F);
        this.stick1_1 = new ModelRenderer(this, 0, 0);
        this.stick1_1.setRotationPoint(0.0F, 0.0F, -3.0F);
        this.stick1_1.addBox(0.0F, 0.0F, -8.0F, 1, 1, 8, 0.0F);
        this.setRotateAngle(stick1_1, 0.29443704481144345F, -0.53249995478347F, 0.045553093477052F);
        this.stick2_1 = new ModelRenderer(this, 0, 0);
        this.stick2_1.setRotationPoint(0.0F, 0.0F, -3.0F);
        this.stick2_1.addBox(0.0F, 0.0F, -8.0F, 1, 1, 8, 0.0F);
        this.setRotateAngle(stick2_1, 0.31869712141416456F, 0.47472955654245763F, -0.045553093477052F);
        this.stick4_2 = new ModelRenderer(this, 0, 0);
        this.stick4_2.setRotationPoint(0.0F, 0.0F, -3.0F);
        this.stick4_2.addBox(0.0F, 0.0F, -8.0F, 1, 1, 8, 0.0F);
        this.setRotateAngle(stick4_2, 18.539759979309764F, 0.18203784098300857F, -44.073926935986805F);
        this.stick1 = new ModelRenderer(this, 0, 10);
        this.stick1.setRotationPoint(0.0F, 0.0F, -3.0F);
        this.stick1.addBox(0.0F, 0.0F, -9.0F, 1, 1, 9, 0.0F);
        this.setRotateAngle(stick1, 0.045553093477052F, -0.40980330836826856F, -0.045553093477052F);
        this.stick3 = new ModelRenderer(this, 0, 10);
        this.stick3.setRotationPoint(0.0F, 0.0F, -3.0F);
        this.stick3.addBox(0.0F, 0.0F, -9.0F, 1, 1, 9, 0.0F);
        this.setRotateAngle(stick3, 18.989531327548704F, -0.21589722847169854F, -44.073926935986805F);
        this.stick4_1 = new ModelRenderer(this, 0, 0);
        this.stick4_1.setRotationPoint(0.0F, 0.0F, -3.0F);
        this.stick4_1.addBox(0.0F, 0.0F, -8.0F, 1, 1, 8, 0.0F);
        this.setRotateAngle(stick4_1, 0.31869712141416456F, 0.22759093446006054F, -0.136659280431156F);
        this.stick2_2 = new ModelRenderer(this, 0, 0);
        this.stick2_2.setRotationPoint(0.0F, 0.0F, -3.0F);
        this.stick2_2.addBox(0.0F, 0.0F, -8.0F, 1, 1, 8, 0.0F);
        this.setRotateAngle(stick2_2, -0.136659280431156F, 0.5009094953223726F, 0.18203784098300857F);
        this.stick4 = new ModelRenderer(this, 0, 10);
        this.stick4.setRotationPoint(0.0F, 0.0F, -3.0F);
        this.stick4.addBox(0.0F, 0.0F, -9.0F, 1, 1, 9, 0.0F);
        this.setRotateAngle(stick4, 0.091106186954104F, 0.12950343049797924F, 0.40980330836826856F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.stick1_2.render(f5);
        this.stick3_1.render(f5);
        this.stick2.render(f5);
        this.stick3_2.render(f5);
        this.handle.render(f5);
        this.stick5.render(f5);
        this.stick1_1.render(f5);
        this.stick2_1.render(f5);
        this.stick4_2.render(f5);
        this.stick1.render(f5);
        this.stick3.render(f5);
        this.stick4_1.render(f5);
        this.stick2_2.render(f5);
        this.stick4.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
