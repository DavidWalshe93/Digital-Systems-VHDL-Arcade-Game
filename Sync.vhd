----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    11:22:08 10/17/2016 
-- Design Name: 
-- Module Name:    Sync - Behavioral 
-- Project Name: 
-- Target Devices: 
-- Tool versions: 
-- Description: 
--
-- Dependencies: 
--
-- Revision: 
-- Revision 0.01 - File Created
-- Additional Comments: 
--
----------------------------------------------------------------------------------
----------------------------------------------------------------------------------
-- VGA Colour Cycle
-- Michelle, 2015
--
-- 
-- Horizontal synchronisation timing:
-- Pulse width:  Tpw = 96 cycles @ 25 MHz
-- Back porch:   Tbp = 48 cycles
-- Display time: Tdisp = 640 cycles
-- Front porch:  Tfp = 16 cycles
-- Sync pulse time (total cycles): Ts = 800 cycles
--
-- Vertical synchronisation timing:
-- Pulse width:  Tpw = 1600 cycles (2 lines) @ 25 MHz
-- Back porch:   Tbp = 23200 cycles (29 lines)
-- Display time: Tdisp = 38400 cycles (480 lines)
-- Front porch:  Tfp = 8000 cycles (10 lines)
-- Sync pulse time (total cycles): Ts = 416800 cycles (521 lines)
----------------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.ALL;
use IEEE.numeric_std.all;

entity VGASync is
  port(clk	  : in std_logic;
	   rst    : in std_logic;
      hSync  : out std_logic;
      vSync  : out std_logic;
	   row    : out std_logic_vector(9 downto 0);
	   col 	  : out std_logic_vector(9 downto 0);
	   vidOn  : out std_logic);
end VGASync;

architecture RTL of VGASync is

-- Constants
constant hCntMax     : integer := 799;
constant vCntMax     : integer := 524;
constant TDispH      : integer := 640;
constant TDispV      : integer := 480;
constant frontPorchH : integer := 16;
constant backPorchH  : integer := 29;
constant pulseWidthH : integer := 96;
constant frontPorchV : integer := 10;
constant backPorchV  : integer := 29;
constant pulseWidthV : integer := 2;
-- Signals
signal hCnt : unsigned(9 downto 0);
signal vCnt : unsigned(9 downto 0);
signal vidOnInt : std_logic;

begin

-- Counter for horizontal sync timing, 0 to hCntMax
-- E.g. hCnt 0 to 799 and repeat
HCounter : process(clk, rst)
begin
	if(rst='0') then
		hCnt  <= (others => '0');
  	elsif(clk'event and clk='1') then
		if hCnt = hCntMax then
      	hCnt <= (others => '0');
		else
			hCnt <= hCnt + 1;	
		end if;
	end if;
end process;

-- Counter for vertical sync timing, 0 to vCntMax
-- E.g. one vCnt every 799 hCnts, vcnt 0 to 524 and repeat
VCounter : process(clk, rst, hCnt)
begin
	if(rst='0') then
		vCnt <= (others => '0');
	elsif(clk'event and clk='1') then
		if(hCnt = hCntMax) then
			if(vCnt = vCntMax) then
				vCnt <= (others => '0');
			else
				vCnt <= vCnt + 1;
			end if;
		end if;
	end if;
end process;

-- Generate horizontal sync pulse
HSyncGen : process(clk, rst, hCnt)
begin
	if(rst='0') then
		hSync <= '1';
	elsif(clk'event and clk='1') then
		if(hCnt > TDispH + frontPorchH and hCnt <= TDispH + frontPorchH + pulseWidthH) then
			hSync <= '0';
		else
			hSync <= '1';
		end if;
	end if;
end process;

-- Generate vertical sync pulse
VSyncGen : process(clk, rst, vCnt)
begin
	if(rst='0') then
		vSync <= '1';
	elsif(clk'event and clk='1') then
		if(vCnt > TDispV + frontPorchV and vCnt <= TDispV + frontPorchV + pulseWidthV) then
			vSync <= '0';
		else
			vSync <= '1';
		end if;
	end if;
end process;

-- Generate video enable signal
VidOnGen : process(clk, rst, hCnt, vCnt)
-- Variables
variable x: unsigned(9 downto 0);
variable y: unsigned(9 downto 0);
begin
	-- E.g. 640 x 480 VGA Display: x coordinate: 0 -> 639, y coordinate: 0 -> 479
	x := hCnt;
	y := vCnt;
	if(rst='0') then
		vidOnInt <= '0';
		x := (others => '0');
		y := (others => '0');
	elsif(clk'event and clk='1') then
		if(x < TDispH and x >= 0 and y < TDispV and y >= 0) then
			vidOnInt <= '1';
		else
			vidOnInt <= '0';
		end if;
	end if;
end process;

-- Drive outputs
vidOn <= vidOnInt;
col   <= std_logic_vector(hCnt);
row   <= std_logic_vector(vCnt);

end RTL;


