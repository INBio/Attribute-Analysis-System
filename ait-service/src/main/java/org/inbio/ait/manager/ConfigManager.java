/* AIT - Analysis of taxonomic indicators
 *
 * Copyright (C) 2010  INBio (Instituto Nacional de Biodiversidad)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.inbio.ait.manager;

import java.util.List;
import org.inbio.ait.model.DwcPropertyHolder;
import org.inbio.ait.model.IndiPropertyHolder;
import org.inbio.ait.model.LayerPropertyHolder;
import org.inbio.ait.model.PlicPropertyHolder;
import org.inbio.ait.model.PostgisLayers;
import org.inbio.ait.model.TindiPropertyHolder;

/**
 *
 * @author esmata
 */
public interface ConfigManager {

    /* Darwin Core */
    public DwcPropertyHolder getDwcPropertyHolder();
    public boolean saveToPropertiesFile(DwcPropertyHolder ph);
    public int CountDwc();
    public List<String> getDwcTableFields();

    /* Plinian Core */
    public PlicPropertyHolder getPlicPropertyHolder();
    public boolean saveToPropertiesFilePlic(PlicPropertyHolder ph);
    public int CountPlic();
    public List<String> getPlicTableFields();

    /* Layers Postgis */
    public LayerPropertyHolder getLayerPropertyHolder();
    public boolean saveToPropertiesFileLayer(LayerPropertyHolder ph);
    public List<String> getLayerTables();
    public int countAllLayerTables();

    /* Indicators */
    public IndiPropertyHolder getIndiPropertyHolder();
    public boolean saveToPropertiesFileIndi(IndiPropertyHolder ph);
    public int CountIndi();
    public List<String> getIndiTableFields();

    /* Taxon Indicator */
    public TindiPropertyHolder getTindiPropertyHolder();
    public boolean saveToPropertiesFileTindi(TindiPropertyHolder ph);
    public int CountTindi();
    public List<String> getTindiTableFields();

    /* Layers local */
    public PostgisLayers getLayersList();
    public boolean saveLayersList(PostgisLayers pl);

    /* Data migration */
    public int migrateDwc();
    public int migrateIndicators();
    public int migrateTaxonIndicators();

    /* Data indexing */
    public boolean taxonIndexProccess();
    public boolean taxonInfoIndexProccess();

}
