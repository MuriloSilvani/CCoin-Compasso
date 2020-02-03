-- ====== User ======

-- Como Usuário ou Administrador, desejo efetuar Login na plataforma.
SELECT  us.id, us.nome, us.nivel_acesso,
        us.cargo, us.unidade, us.credito,
        us.responsavel_unidade FROM liveb09.usuarios us
    WHERE (us.login = 'username' OR us.email = 'username')
    AND us.senha = '123'
    AND us.nivel_acesso IN (SELECT ni.id FROM liveb09.niveis_acesso ni)
    AND us.cargo IN (SELECT ca.id FROM liveb09.cargos ca)
    AND us.unidade IN (SELECT un.id FROM liveb09.unidades un);

-- Como Usuário ou Administrador, desejo efetuar Logout na plataforma.


-- ====== Itens ======

-- Como Administrador desejo manipular (incluir, editar ou inativar) itens no catálogo.
-- Cadastrar
-- Editar
-- Inativar


-- == Transferencia ==
--      Envia
UPDATE liveb09.usuarios us SET us.credito =
	CASE
        WHEN us.credito >= 900 THEN
		    us.credito - 900
        WHEN us.credito < 900 THEN
            us.credito
	END
	WHERE us.id = 1;
--      Recebe
UPDATE liveb09.usuarios us
    SET us.credito = us.credito + 900
    WHERE us.id = 1;
-- Transfere


-- Fluxo para Consulta de Transferências.



-- Fluxo para Consulta de Extrato por Usuário.
SELECT CONCAT(
        (CASE
            WHEN tr.usuario_origem = 1 THEN '- '
            WHEN tr.usuario_destino = 1 THEN '+ '
		END), tr.valor
    ) AS saldo,
    CASE
        WHEN st.id_status = 0 THEN 'Em Analise'
        WHEN st.id_status = 1 THEN 'Aprovado'
        WHEN st.id_status = 2 THEN 'A Caminho'
        WHEN st.id_status = 3 THEN 'Entregue'
        WHEN st.id_status = 4 THEN 'Cancelado'
        WHEN st.id_status = 5 THEN 'Reprovado'
    END AS status
    FROM liveb08.trasferencias tr
    JOIN liveb08.status_requerimentos st
        ON st.id_transferencia = tr.id
    WHERE tr.usuario_origem = 1
        OR tr.usuario_destino = 1;

-- Fluxo para Consulta de Saldo.
SELECT us.nome, us.credito AS Saldo
    FROM liveb09.usuarios us
    WHERE us.id = 1;
