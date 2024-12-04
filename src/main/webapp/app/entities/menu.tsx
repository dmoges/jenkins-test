import MenuItem from 'app/shared/layout/menus/menu-item';
import React from 'react';
import { Translate } from 'react-jhipster';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/professor">
        <Translate contentKey="global.menu.entities.professor" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/speciality">
        <Translate contentKey="global.menu.entities.speciality" />
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;
