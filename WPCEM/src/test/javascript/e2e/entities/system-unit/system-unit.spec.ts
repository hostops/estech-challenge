/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { SystemUnitComponentsPage, SystemUnitDeleteDialog, SystemUnitUpdatePage } from './system-unit.page-object';

const expect = chai.expect;

describe('SystemUnit e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let systemUnitUpdatePage: SystemUnitUpdatePage;
    let systemUnitComponentsPage: SystemUnitComponentsPage;
    let systemUnitDeleteDialog: SystemUnitDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load SystemUnits', async () => {
        await navBarPage.goToEntity('system-unit');
        systemUnitComponentsPage = new SystemUnitComponentsPage();
        await browser.wait(ec.visibilityOf(systemUnitComponentsPage.title), 5000);
        expect(await systemUnitComponentsPage.getTitle()).to.eq('wpcemApp.systemUnit.home.title');
    });

    it('should load create SystemUnit page', async () => {
        await systemUnitComponentsPage.clickOnCreateButton();
        systemUnitUpdatePage = new SystemUnitUpdatePage();
        expect(await systemUnitUpdatePage.getPageTitle()).to.eq('wpcemApp.systemUnit.home.createOrEditLabel');
        await systemUnitUpdatePage.cancel();
    });

    it('should create and save SystemUnits', async () => {
        const nbButtonsBeforeCreate = await systemUnitComponentsPage.countDeleteButtons();

        await systemUnitComponentsPage.clickOnCreateButton();
        await promise.all([
            systemUnitUpdatePage.setNameInput('name'),
            systemUnitUpdatePage.setDescriptionInput('description')
            // systemUnitUpdatePage.usersSelectLastOption(),
        ]);
        expect(await systemUnitUpdatePage.getNameInput()).to.eq('name');
        expect(await systemUnitUpdatePage.getDescriptionInput()).to.eq('description');
        await systemUnitUpdatePage.save();
        expect(await systemUnitUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await systemUnitComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last SystemUnit', async () => {
        const nbButtonsBeforeDelete = await systemUnitComponentsPage.countDeleteButtons();
        await systemUnitComponentsPage.clickOnLastDeleteButton();

        systemUnitDeleteDialog = new SystemUnitDeleteDialog();
        expect(await systemUnitDeleteDialog.getDialogTitle()).to.eq('wpcemApp.systemUnit.delete.question');
        await systemUnitDeleteDialog.clickOnConfirmButton();

        expect(await systemUnitComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
