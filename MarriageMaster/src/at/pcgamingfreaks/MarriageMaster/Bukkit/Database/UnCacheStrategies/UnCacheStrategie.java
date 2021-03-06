/*
 * Copyright (C) 2016, 2018 GeorgH93
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package at.pcgamingfreaks.MarriageMaster.Bukkit.Database.UnCacheStrategies;

import at.pcgamingfreaks.MarriageMaster.Bukkit.Database.MarriageData;
import at.pcgamingfreaks.MarriageMaster.Bukkit.Database.MarriagePlayerData;
import at.pcgamingfreaks.MarriageMaster.Bukkit.MarriageMaster;
import at.pcgamingfreaks.MarriageMaster.Database.Cache;

public abstract class UnCacheStrategie
{
	protected final Cache<MarriagePlayerData, MarriageData> cache;

	public UnCacheStrategie(Cache cache)
	{
		//noinspection unchecked
		this.cache = cache;
	}

	public static UnCacheStrategie getUnCacheStrategie(Cache cache)
	{
		switch(MarriageMaster.getInstance().getConfiguration().getUnCacheStrategie())
		{
			case "ondisconnect": return new OnDisconnect(cache);
			case "ondisconnectdelayed": return new OnDisconnectDelayed(cache);
			case "intervalChecked": return new IntervalChecked(cache);
			case "interval": default: return new Interval(cache);
		}
	}

	public abstract void close();
}